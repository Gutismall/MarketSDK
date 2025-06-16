// services/postService.ts
import axios from "axios";
import api from "./api";

export async function getPostsForCategory(
  appId: String,
  marketId: string,
  categoryId: string
) {
  try {
    const response = await api.get(
      `/api/${appId}/markets/${marketId}/posts/${categoryId}`
    );
    return response.data;
  } catch (error) {
    console.error("Failed to fetch posts:", error);
    return [];
  }
}

export async function getNumberOfPostsPerMarket(
  appId: String,
  marketId: String
) {
  try {
    const response = await api.get(
      `/api/${appId}/markets/${marketId}/posts/count`
    );
    return response.data;
  } catch (error) {
    console.error("Failed to fetch posts:", error);
  }
}
export async function fetchPriceOfPosts(appId: String, marketId: string) {
  try {
    const response = await api.get(
      `/api/${appId}/markets/${marketId}/posts/price`
    );
    return response.data;
  } catch (error) {
    console.error("Failed to fetch posts:", error);
    return [];
  }
}
