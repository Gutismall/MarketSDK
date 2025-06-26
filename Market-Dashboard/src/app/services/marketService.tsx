import axios from "axios";
import api from "./api";

export async function fetchMarkets(appId: string) {
  try {
    const response = await api.get(`/api/${appId}/markets`);
    return response.data;
  } catch (err: any) {
    throw new Error(err.response?.data?.message || "Failed to load markets");
  }
}

export async function addNewMarket(
  appId: string,
  marketDetails: { name: string; categoriesIds: string[] }
) {
  try {
    const response = await api.post(`/api/${appId}`, marketDetails);
    console.log(`${marketDetails.categoriesIds}`);
    return response.data; // Return the response data (e.g., the created market)
  } catch (err: any) {
    throw new Error(err.response?.data?.message || "Failed to add new market");
  }
}
