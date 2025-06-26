// components/RegisterForm.tsx
"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { registerUser } from "@/app/services/registerService";

export default function RegisterForm() {
  const [email, setEmail] = useState("");
  const [appId, setAppId] = useState("");
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState("");
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    const result = await registerUser(email, appId);
    console.log("Result:", result);

    if (result.success) {
      console.log(`User registered successfully: ${result.data}`);
      setSuccess("Registered successfully");
      router.push("/auth/login"); // Redirect to the login page
    } else {
      console.error(`Registration failed: ${result.message}`);
      setSuccess(result.message || "Registration failed");
    }

    setLoading(false);
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <input
        type="email"
        placeholder="Email"
        className="w-full p-3 border rounded"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="App ID"
        className="w-full p-3 border rounded"
        value={appId}
        onChange={(e) => setAppId(e.target.value)}
        required
      />
      <button
        type="submit"
        disabled={loading}
        className="bg-blue-600 text-white px-4 py-2 rounded w-full"
      >
        {loading ? "Registering..." : "Register"}
      </button>
      {success && <p className="text-red-600">{success}</p>}
    </form>
  );
}
