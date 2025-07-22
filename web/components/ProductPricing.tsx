'use client';

import type { Product } from '@/types/product';

interface ProductPricingProps {
  readonly product: Product;
}

export default function ProductPricing({ product }: ProductPricingProps) {
  const formatPrice = (price: number) => {
    return new Intl.NumberFormat('es-AR', {
      style: 'currency',
      currency: product.currency,
      minimumFractionDigits: 0,
    }).format(price);
  };

  return (
    <div className="space-y-2">
      <div className="text-3xl font-light text-gray-900">
        {formatPrice(product.price)}
      </div>
      <div className="text-lg text-green-600 font-normal">
        {product.paymentOptions.installments}
      </div>
      <button className="text-sm text-blue-600 hover:text-blue-700">
        Ver los medios de pago
      </button>
    </div>
  );
}
