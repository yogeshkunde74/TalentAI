import { FaUserCircle } from "react-icons/fa";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import { loginUser } from "../../services/authService";
import { Toaster } from "react-hot-toast";

export default function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!email || !password) {
      toast.error("Please fill all fields");

      return;
    }

    try {
      const response = await loginUser({
        email,
        password,
      });

      localStorage.setItem("token", response.data.token);

      toast.success("Login Successful");

      navigate("/dashboard");
    } catch (error) {
      toast.error("Invalid Credentials");
    }
  };

  return (
    <>
      <Toaster position="top-right" />
      <div className="min-h-screen bg-slate-900 flex items-center justify-center">
        <div className="bg-white w-[420px] rounded-xl shadow-lg p-8">
          <div className="flex justify-center">
            <FaUserCircle size={70} className="text-blue-600" />
          </div>

          <h1 className="text-3xl font-bold text-center mt-4">Welcome Back</h1>

          <p className="text-center text-gray-500 mt-2">Login to TalentAI</p>

          <form onSubmit={handleLogin} className="mt-8">
            <div className="mb-5">
              <label className="font-semibold">Email</label>

              <input
                type="email"
                placeholder="Enter Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full mt-2 border rounded-lg p-3 outline-none focus:border-blue-500"
              />
            </div>

            <div className="mb-6">
              <label className="font-semibold">Password</label>

              <input
                type="password"
                placeholder="Enter Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full mt-2 border rounded-lg p-3 outline-none focus:border-blue-500"
              />
            </div>

            <button
              type="submit"
              className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg font-semibold transition"
            >
              Login
            </button>
          </form>
        </div>
      </div>
    </>
  );
}
