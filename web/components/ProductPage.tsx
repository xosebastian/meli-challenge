'use client';

import { Card, CardContent } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import {
  Heart,
  Share2,
  ShoppingCart,
  Truck,
  Shield,
  MapPin,
  Clock,
  Star,
} from 'lucide-react';
import Image from 'next/image';
import { useState } from 'react';
import { useProduct } from '@/hooks/useProduct';
import ProductHeader from './ProductHeader';
import ProductPricing from './ProductPricing';
import ProductReviews from './ProductReviews';

interface ProductPageProps {
  readonly productId: string;
}

export default function ProductPage({ productId }: ProductPageProps) {
  const { data: product, isLoading, error } = useProduct(productId);
  const [selectedImage, setSelectedImage] = useState(0);

  if (isLoading) {
    return (
      <div className="min-h-screen bg-gray-100">
        <div className="animate-pulse">
          <div className="bg-yellow-400 h-16"></div>
          <div className="max-w-7xl mx-auto px-4 py-6">
            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
              <div className="lg:col-span-2">
                <div className="bg-white rounded p-6">
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div className="aspect-square bg-gray-200 rounded"></div>
                    <div className="space-y-4">
                      <div className="h-6 bg-gray-200 rounded"></div>
                      <div className="h-8 bg-gray-200 rounded"></div>
                      <div className="h-4 bg-gray-200 rounded w-3/4"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  if (error || !product) {
    return (
      <div className="min-h-screen bg-gray-100 flex items-center justify-center">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-gray-900 mb-2">
            Error al cargar el producto
          </h2>
          <p className="text-gray-600">
            Por favor, intenta nuevamente más tarde.
          </p>
          <p className="text-sm text-red-600 mt-2">
            Error: {error?.message || 'Producto no encontrado'}
          </p>
          <Button className="mt-4" onClick={() => window.location.reload()}>
            Reintentar
          </Button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100">
      {/* Header */}
      <header className="bg-yellow-400 px-4 py-3">
        <div className="max-w-7xl mx-auto flex items-center gap-4">
          <div className="flex items-center gap-2">
            <Image
              src="/mercadolibre-logo.png"
              alt="MercadoLibre"
              width={120}
              height={40}
              className="h-8 w-auto object-contain"
              priority
            />
          </div>
          <div className="flex-1 max-w-xl">
            <input
              type="text"
              placeholder="Buscar productos, marcas y más..."
              className="w-full px-4 py-2 rounded-sm text-sm bg-white text-gray-900 placeholder-gray-500"
            />
          </div>
        </div>
      </header>

      {/* Breadcrumb */}
      <div className="bg-white px-4 py-2 text-xs text-gray-600 border-b">
        <div className="max-w-7xl mx-auto">
          <nav className="flex items-center space-x-2">
            <span className="hover:underline cursor-pointer">Inicio</span>
            <span className="mx-2">{'>'}</span>
            <span className="hover:underline cursor-pointer">
              {product.metadata.category}
            </span>
            <span className="mx-2">{'>'}</span>
            <span className="hover:underline cursor-pointer">
              {product.metadata.brand}
            </span>
          </nav>
        </div>
      </div>

      <div className="max-w-7xl mx-auto px-4 py-6">
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
          {/* Main Content */}
          <div className="lg:col-span-2">
            <Card className="overflow-hidden">
              <CardContent className="p-6">
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                  {/* Product Images */}
                  <div className="space-y-4">
                    <div className="aspect-square bg-white rounded-lg overflow-hidden border">
                      <Image
                        src={
                          product.images[selectedImage] || '/placeholder.svg'
                        }
                        alt={`${product.title} - imagen ${selectedImage + 1}`}
                        width={500}
                        height={500}
                        className="w-full h-full object-contain"
                        priority
                      />
                    </div>

                    {product.images.length > 1 && (
                      <div className="flex gap-2 overflow-x-auto">
                        {product.images.map((image, index) => (
                          <button
                            key={index}
                            onClick={() => setSelectedImage(index)}
                            className={`flex-shrink-0 w-16 h-16 rounded border-2 overflow-hidden ${
                              selectedImage === index
                                ? 'border-blue-500'
                                : 'border-gray-200'
                            }`}
                          >
                            <Image
                              src={image || '/placeholder.svg'}
                              alt={`${product.title} - miniatura ${index + 1}`}
                              width={64}
                              height={64}
                              className="w-full h-full object-contain bg-white"
                            />
                          </button>
                        ))}
                      </div>
                    )}
                  </div>

                  {/* Product Details */}
                  <div className="space-y-6">
                    <ProductHeader product={product} />
                    <ProductPricing product={product} />

                    {/* Shipping Info */}
                    <div className="space-y-3 py-4 border-t border-gray-200">
                      <div className="flex items-center gap-2 text-sm">
                        <Truck className="w-4 h-4 text-green-600 flex-shrink-0" />
                        <div>
                          <span className="text-green-600 font-medium">
                            {product.delivery.cost === 0
                              ? 'Llega gratis'
                              : 'Envío pago'}{' '}
                            el{' '}
                            {new Date(
                              product.delivery.estimatedDate
                            ).toLocaleDateString('es-AR')}
                          </span>
                          <div className="text-xs text-gray-600">
                            Comprando dentro de las próximas 2 horas
                          </div>
                        </div>
                      </div>

                      <div className="flex items-start gap-2 text-sm">
                        <Shield className="w-4 h-4 text-blue-600 flex-shrink-0 mt-0.5" />
                        <div>
                          <span className="font-medium">Compra Protegida</span>
                          <div className="text-xs text-gray-600">
                            Recibí el producto que esperabas o te devolvemos tu
                            dinero
                          </div>
                        </div>
                      </div>

                      <div className="flex items-center gap-2 text-sm">
                        <MapPin className="w-4 h-4 text-gray-600 flex-shrink-0" />
                        <span>
                          <span className="font-medium text-green-600">
                            Stock disponible
                          </span>
                          {product.stock < 10 && (
                            <span className="text-orange-600 ml-1 font-medium">
                              (¡Últimas {product.stock} unidades!)
                            </span>
                          )}
                        </span>
                      </div>

                      <div className="flex items-center gap-2 text-sm">
                        <Clock className="w-4 h-4 text-gray-600 flex-shrink-0" />
                        <span>
                          Vendido por{' '}
                          <span className="font-medium">
                            {product.seller.name}
                          </span>
                        </span>
                      </div>
                    </div>

                    {/* Actions */}
                    <div className="space-y-3">
                      <div className="flex gap-3">
                        <Button className="flex-1 bg-blue-500 hover:bg-blue-600 text-white py-3 font-medium">
                          Comprar ahora
                        </Button>
                        <Button
                          variant="outline"
                          className="flex-1 bg-transparent border-blue-500 text-blue-500 hover:bg-blue-50 py-3 font-medium"
                        >
                          <ShoppingCart className="w-4 h-4 mr-2" />
                          Agregar al carrito
                        </Button>
                      </div>

                      <div className="flex items-center justify-center gap-6 pt-2 text-sm">
                        <button
                          className={`flex items-center gap-1 transition-colors `}
                        >
                          <Heart className={`w-4 h-4}`} />
                          Agregar a favoritos
                        </button>
                        <button className="flex items-center gap-1 text-blue-600 hover:text-blue-700 transition-colors">
                          <Share2 className="w-4 h-4" />
                          Compartir
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </CardContent>
            </Card>

            {/* Product Features */}
            <Card className="mt-6">
              <CardContent className="p-6">
                <h2 className="text-xl font-medium mb-4">
                  Características principales
                </h2>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  {product.features.map((feature, index) => (
                    <div
                      key={index}
                      className="flex justify-between py-2 border-b border-gray-100"
                    >
                      <span className="text-gray-600">{feature.name}</span>
                      <span className="font-medium text-gray-900">
                        {feature.value}
                      </span>
                    </div>
                  ))}
                </div>
              </CardContent>
            </Card>

            {/* Description */}
            <Card className="mt-6">
              <CardContent className="p-6">
                <h2 className="text-xl font-medium mb-4">Descripción</h2>
                <p className="text-gray-700 leading-relaxed whitespace-pre-wrap">
                  {product.description}
                </p>
              </CardContent>
            </Card>

            {/* Metadata */}
            <Card className="mt-6">
              <CardContent className="p-6">
                <h2 className="text-xl font-medium mb-4">
                  Información del producto
                </h2>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  <div className="space-y-3">
                    <div className="flex justify-between py-2 border-b border-gray-100">
                      <span className="text-gray-600">Categoría</span>
                      <span className="font-medium text-gray-900">
                        {product.metadata.category}
                      </span>
                    </div>
                    <div className="flex justify-between py-2 border-b border-gray-100">
                      <span className="text-gray-600">Marca</span>
                      <span className="font-medium text-gray-900">
                        {product.metadata.brand}
                      </span>
                    </div>
                    <div className="flex justify-between py-2 border-b border-gray-100">
                      <span className="text-gray-600">Estado</span>
                      <span className="font-medium text-gray-900">
                        {product.metadata.availability === 'in_stock'
                          ? 'Nuevo'
                          : 'Usado'}
                      </span>
                    </div>
                  </div>
                </div>
              </CardContent>
            </Card>

            {/* Reviews */}
            <ProductReviews productId={productId} />
          </div>

          {/* Sidebar */}
          <div className="space-y-4">
            {/* Seller Info */}
            <Card>
              <CardContent className="p-4">
                <div className="flex items-center gap-3 mb-4">
                  <div className="w-10 h-10 bg-blue-100 rounded-full flex items-center justify-center">
                    <span className="text-sm font-bold text-blue-600">
                      {product.seller.name.substring(0, 2).toUpperCase()}
                    </span>
                  </div>
                  <div className="flex-1">
                    <div className="font-medium text-sm flex items-center gap-2">
                      {product.seller.name}
                    </div>
                    <Badge
                      variant="secondary"
                      className="text-xs bg-purple-100 text-purple-800"
                    >
                      {product.seller.reputation}
                    </Badge>
                  </div>
                </div>

                <div className="space-y-3 text-sm">
                  <div className="flex justify-between items-center">
                    <span className="text-gray-600">Ventas</span>
                    <span className="font-medium text-green-600">
                      {product.seller.reviews > 1000
                        ? `${(product.seller.reviews / 1000).toFixed(1)}k`
                        : product.seller.reviews}
                    </span>
                  </div>

                  <div className="flex justify-between items-center">
                    <span className="text-gray-600">Calificación</span>
                    <div className="flex items-center gap-1">
                      <Star className="w-3 h-3 fill-orange-400 text-orange-400" />
                      <span className="font-medium text-green-600">
                        {product.seller.rating}
                      </span>
                    </div>
                  </div>
                </div>

                <Button
                  variant="outline"
                  className="w-full mt-4 text-sm bg-transparent"
                >
                  Ver más productos
                </Button>
              </CardContent>
            </Card>

            {/* Payment Methods */}
            <Card>
              <CardContent className="p-4">
                <h3 className="font-medium mb-3">Medios de pago</h3>
                <div className="space-y-2">
                  {product.paymentOptions.methods.map((method, index) => (
                    <div
                      key={index}
                      className="flex items-center gap-2 text-sm"
                    >
                      <div className="w-2 h-2 bg-blue-600 rounded-full"></div>
                      <span>{method}</span>
                    </div>
                  ))}
                </div>
                <div className="text-xs text-gray-600 mt-2">
                  Hasta 12 cuotas sin interés
                </div>
              </CardContent>
            </Card>

            {/* Warranty */}
            <Card>
              <CardContent className="p-4">
                <h3 className="font-medium mb-2">Garantía</h3>
                <p className="text-sm text-gray-700">
                  Garantía del vendedor: 12 meses
                </p>
              </CardContent>
            </Card>
          </div>
        </div>
      </div>
    </div>
  );
}
