export interface Post {
  postId: string;
  title: string;
  description: string;
  price: number;
  marketId: string;
  categoryId: string;
  images?: string[]; // Optional field
  userID?: string; // Optional field
  country?: string; // Optional field
  city?: string; // Optional field
  createdAt?: string; // Optional field
}
