// Types matching the Spring Boot API ProductResponseDto
export interface Product {
  id: string;
  title: string;
  description: string;
  price: number;
  currency: string;
  stock: number;
  images: string[];
  variants: Variant[];
  paymentOptions: PaymentOptions;
  delivery: Delivery;
  seller: Seller;
  ratings: Ratings;
  features: Feature[];
  reviews?: Review[]; // Optional field - backend doesn't always include it
  metadata: Metadata;
}

export interface Variant {
  label: string;
  options: string[];
}

export interface PaymentOptions {
  methods: string[];
  installments: string;
}

export interface Delivery {
  type: string;
  estimatedDate: string;
  cost: number;
}

export interface Seller {
  name: string;
  rating: number;
  reviews: number;
  reputation: string;
}

export interface Ratings {
  average: number;
  count: number;
}

export interface Feature {
  name: string;
  value: string;
}

export interface Review {
  user: string;
  rating: number;
  comment: string;
}

export interface Metadata {
  seoTitle: string;
  seoDescription: string;
  category: string;
  brand: string;
  availability: string;
}
