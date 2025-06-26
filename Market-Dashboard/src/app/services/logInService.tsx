import api from "./api";

export async function loginUser(email: string, appId: string) {
  try {
    const response = await api.get(`/users`, {
      params: {
        email,
        appId,
      },
    });
    if (response.status === 200) {
      return { success: true, data: response.data };
    } else {
      return { success: false, message: "Unexpected response status" };
    }
  } catch (error: any) {
    console.error("Login error:", error);
    throw new Error(error.response?.data?.message || "Login failed");
  }
}
