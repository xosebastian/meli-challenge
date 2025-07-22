'use client';

import { Star } from 'lucide-react';
import type { Product } from '@/types/product';

interface ProductHeaderProps {
  readonly product: Product;
}

export default function ProductHeader({ product }: ProductHeaderProps) {
  return (
    <div className="space-y-4">
      {/* Condition & Sales */}
      <div className="flex items-center gap-4 text-sm text-gray-600">
        <span className="text-gray-500">
          {product.metadata.availability === 'in_stock' ? 'Nuevo' : 'Usado'}
        </span>
        <span className="text-gray-400">|</span>
        <span className="text-gray-600">+{product.ratings.count} vendidos</span>
      </div>

      {/* Title */}
      <h1 className="text-2xl font-normal text-gray-900 leading-7">
        {product.title}
      </h1>

      {/* Rating */}
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
        <span className="text-sm text-gray-600">{product.ratings.average}</span>
        <span className="text-sm text-gray-400">
          ({product.ratings.count} opiniones)
        </span>
      </div>
    </div>
  );
}
