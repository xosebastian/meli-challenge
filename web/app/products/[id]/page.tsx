import ProductPage from '@/components/ProductPage';
import { Metadata } from 'next';
import { fetchProduct } from '@/lib/api';
import { cache } from 'react';

interface PageProps {
  params: Promise<{
    id: string;
  }>;
}

// Cache the product fetch to avoid duplicate calls
const getProduct = cache(async (id: string) => {
  return await fetchProduct(id);
});

export async function generateMetadata({
  params,
}: PageProps): Promise<Metadata> {
  const { id } = await params;

  try {
    const product = await getProduct(id);
    return {
      title: `${product.title} | MercadoLibre`,
      description:
        product.metadata.seoDescription ||
        product.description.substring(0, 160),
    };
  } catch (error) {
    console.error('Error fetching product:', error);
    return {
      title: 'Producto no encontrado | MercadoLibre',
      description: 'El producto que buscas no está disponible',
    };
  }
}

export default async function ProductDetailPage({ params }: PageProps) {
  const { id } = await params;
  return <ProductPage productId={id} />;
}
