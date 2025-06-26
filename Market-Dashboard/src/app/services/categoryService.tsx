// services/categoryService.ts
import api from "./api";

export async function getCategoriesForMarket(appId: string, marketId: string) {
  try {
    const response = await api.get(`/api/${appId}/${marketId}/category`);
    return response.data;
  } catch (error) {
    console.error("Failed to fetch categories:", error);
    return [];
  }
}

export async function createCategories(
  appId: string,
  marketId: string,
  categories: string[]
) {
  try {
    for (const category of categories) {
      const respons = await api.post(`/api/${appId}/${marketId}`, { category });
    }
  } catch (error) {
    console.error("Failed to create categories:", error);
  }
}
