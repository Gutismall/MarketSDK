// services/authService.ts

import api from "./api";

export async function registerUser(email: string, appId: string) {
  try {
    const response = await api.post("/users/register", {
      email: email,
      appId: appId,
    });

    if (response.status === 200) {
      return { success: true, data: response.data }; // Return success indicator
    } else {
      return { success: false, message: "Unexpected response status" };
    }
  } catch (err: any) {
    console.error("Registration error:", err);
    return {
      success: false,
      message: err.response?.data?.message || "Registration failed",
    };
  }
}
