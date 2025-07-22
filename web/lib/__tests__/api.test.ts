import { fetchProduct } from '../api'

// Mock fetch globally
global.fetch = jest.fn()

const mockFetch = global.fetch as jest.MockedFunction<typeof fetch>

describe('API', () => {
  beforeEach(() => {
    jest.clearAllMocks()
  })

  describe('fetchProduct', () => {
    it('should fetch product successfully', async () => {
      const mockProduct = {
        id: '1',
        title: 'Test Product',
        price: 100,
        originalPrice: 120,
        discount: 20,
        description: 'Test description',
        condition: 'new',
        images: ['image1.jpg'],
        seller: {
          id: 'seller1',
          name: 'Test Seller',
        },
        ratings: {
          average: 4.5,
          count: 100,
        },
        metadata: {
          category: 'Electronics',
          brand: 'Test Brand',
          availability: 'in_stock',
          seoTitle: 'Test SEO Title',
          seoDescription: 'Test SEO Description',
        },
        currency: 'ARS',
        stock: 10,
        variants: [],
        paymentOptions: [],
        delivery: {
          cost: 0,
          estimatedDays: 3,
        },
        reviews: [],
      }

      mockFetch.mockResolvedValueOnce({
        ok: true,
        json: async () => mockProduct,
      } as Response)

      const result = await fetchProduct('1')

      expect(result).toEqual(mockProduct)
      expect(mockFetch).toHaveBeenCalledWith(
        expect.stringContaining('/api/products/1'),
        expect.objectContaining({
          headers: {
            'Content-Type': 'application/json',
          },
        })
      )
    })

    it('should throw error when API returns error status', async () => {
      mockFetch.mockResolvedValueOnce({
        ok: false,
        status: 404,
        statusText: 'Not Found',
      } as Response)

      await expect(fetchProduct('1')).rejects.toThrow('API Error: 404 Not Found')
    })

    it('should throw error when network request fails', async () => {
      const networkError = new Error('Network error')
      mockFetch.mockRejectedValueOnce(networkError)

      await expect(fetchProduct('1')).rejects.toThrow('Network error')
    })

    it('should use correct API URL', async () => {
      const mockProduct = {
        id: '1',
        title: 'Test Product',
        price: 100,
        originalPrice: 120,
        discount: 20,
        description: 'Test description',
        condition: 'new',
        images: ['image1.jpg'],
        seller: {
          id: 'seller1',
          name: 'Test Seller',
        },
        ratings: {
          average: 4.5,
          count: 100,
        },
        metadata: {
          category: 'Electronics',
          brand: 'Test Brand',
          availability: 'in_stock',
          seoTitle: 'Test SEO Title',
          seoDescription: 'Test SEO Description',
        },
        currency: 'ARS',
        stock: 10,
        variants: [],
        paymentOptions: [],
        delivery: {
          cost: 0,
          estimatedDays: 3,
        },
        reviews: [],
      }

      mockFetch.mockResolvedValueOnce({
        ok: true,
        json: async () => mockProduct,
      } as Response)

      await fetchProduct('123')

      expect(mockFetch).toHaveBeenCalledWith(
        expect.stringMatching(/\/api\/products\/123$/),
        expect.any(Object)
      )
    })
  })
}) 