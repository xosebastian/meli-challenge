'use client';

import { Card, CardContent } from '@/components/ui/card';
import { Star } from 'lucide-react';
import { useProduct } from '@/hooks/useProduct';

interface ProductReviewsProps {
  readonly productId: string;
}

export default function ProductReviews({
  productId,
}: Readonly<ProductReviewsProps>) {
  const { data: product } = useProduct(productId);

  if (
    !product?.reviews ||
    !Array.isArray(product.reviews) ||
    product.reviews.length === 0
  ) {
    return (
      <Card className="mt-6">
        <CardContent className="p-6">
          <h2 className="text-xl font-medium mb-4">Opiniones del producto</h2>
          <p className="text-gray-500">
            No hay opiniones disponibles para este producto.
          </p>
        </CardContent>
      </Card>
    );
  }

  return (
    <Card className="mt-6">
      <CardContent className="p-6">
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-xl font-medium">Opiniones del producto</h2>
          <div className="flex items-center gap-2">
            <div className="flex items-center">
              {Array.from({ length: 5 }).map((_, i) => (
                <Star
                  key={i}
                  className={`w-4 h-4 ${
                    i < Math.floor(product.ratings.average)
                      ? 'fill-orange-400 text-orange-400'
                      : 'text-gray-300'
                  }`}
                />
              ))}
            </div>
            <span className="font-medium">{product.ratings.average}</span>
            <span className="text-sm text-gray-500">
              ({product.ratings.count} opiniones)
            </span>
          </div>
        </div>

        <div className="space-y-4">
          {product.reviews.slice(0, 5).map((review, index) => (
            <div
              key={index}
              className="border-b border-gray-100 pb-4 last:border-b-0"
            >
              <div className="flex items-start gap-3 mb-2">
                <div className="w-8 h-8 bg-gray-300 rounded-full flex items-center justify-center">
                  <span className="text-sm font-medium text-gray-700">
                    {review.user.charAt(0).toUpperCase()}
                  </span>
                </div>
                <div className="flex-1">
                  <div className="flex items-center gap-2 mb-1">
                    <span className="font-medium text-sm">{review.user}</span>
                    <div className="flex items-center">
                      {Array.from({ length: 5 }).map((_, i) => (
                        <Star
                          key={i}
                          className={`w-3 h-3 ${
                            i < review.rating
                              ? 'fill-orange-400 text-orange-400'
                              : 'text-gray-300'
                          }`}
                        />
                      ))}
                    </div>
                  </div>
                  <p className="text-sm text-gray-700 leading-relaxed">
                    {review.comment}
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>

        <div className="pt-4 border-t">
          <button className="text-blue-600 hover:text-blue-700 text-sm font-medium">
            Ver todas las opiniones
          </button>
        </div>
      </CardContent>
    </Card>
  );
}
