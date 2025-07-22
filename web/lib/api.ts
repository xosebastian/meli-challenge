import type { Product } from '@/types/product';

// Use Next.js proxy to avoid CORS issues
// This will route through http://localhost:3000/api/* -> http://localhost:8080/*
const API_BASE_URL =
  typeof window !== 'undefined'
    ? '/api' // Use proxy in browser
    : process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080'; // Direct call on server
class ApiClient {
  readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  private async request<T>(endpoint: string): Promise<T> {
    const url = `${this.baseUrl}${endpoint}`;

    const response = await fetch(url, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`API Error: ${response.status} ${response.statusText}`);
    }

    const data = await response.json();

    return data;
  }

  async getProduct(id: string): Promise<Product> {
    return this.request<Product>(`/products/${id}`);
  }

  async healthCheck(): Promise<{ status: string }> {
    return this.request<{ status: string }>('/actuator/health');
  }
}

export const apiClient = new ApiClient(API_BASE_URL);

export const fetchProduct = (id: string) => apiClient.getProduct(id);
export const healthCheck = () => apiClient.healthCheck();
