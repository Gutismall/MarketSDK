"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { loginUser } from "../services/logInService";
import { setAppIdCookie } from "../services/coockies";
import api from "../services/api";

export default function LoginForm() {
  const [email, setEmail] = useState("");
  const [appId, setAppId] = useState("");
  const [error, setError] = useState("");
  const [testResponse, setTestResponse] = useState(""); // State for test button response
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");

    const result = await loginUser(email, appId);
    console.log("Result:", result);

    if (result.success) {
      console.log(`User log in successfully: ${result.data}`);
      localStorage.setItem("appId", appId);
      router.push("/dashboard"); // Redirect to the login page
    } else {
      console.error(`log in failed: ${result.message}`);
    }
  };

  const handleTestRequest = async () => {
    try {
      const res = await api.get("/users/hello"); // Send GET request to /users/hello
      setTestResponse(
        typeof res.data === "string" ? res.data : JSON.stringify(res.data)
      ); // Update state with the response
      console.log("Response:", res.data);
    } catch (err) {
      console.error("Error:", err);
      setTestResponse("Error occurred while making the request.");
    }
  };

  return (
    <div>
      <form
        onSubmit={handleSubmit}
        className="p-4 space-y-4 border rounded shadow w-80"
      >
        <h2 className="text-xl font-bold">Login</h2>
        <input
          type="email"
          placeholder="Email"
          className="w-full p-2 border rounded"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="App ID"
          className="w-full p-2 border rounded"
          value={appId}
          onChange={(e) => setAppId(e.target.value)}
          required
        />
        {error && <p className="text-red-500">{error}</p>}
        <button
          type="submit"
          className="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          Login
        </button>
        <button
          type="button"
          onClick={() => router.push("/auth/register")}
          className="w-full p-2 bg-gray-500 text-white rounded hover:bg-gray-600"
        >
          Go to Register
        </button>
      </form>
      <div className="mt-4">
        <button
          onClick={handleTestRequest}
          className="w-full p-2 bg-green-500 text-white rounded hover:bg-green-600"
        >
          Test GET Request
        </button>
        {testResponse && <p className="mt-4">Response: {testResponse}</p>}
      </div>
    </div>
  );
}
