package com.talentai.backend.file.service.impl;

import com.talentai.backend.file.service.PdfService;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public String extractText(MultipartFile file) {

        try (
                PDDocument document = Loader.loadPDF(file.getBytes())
        ) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);

        } catch (IOException e) {

            throw new RuntimeException("Unable to read PDF", e);

        }

    }
}