// Simple utility function for testing
export const formatPrice = (price: number, currency: string = 'ARS'): string => {
  return new Intl.NumberFormat('es-AR', {
    style: 'currency',
    currency,
    minimumFractionDigits: 0,
  }).format(price)
}

export const formatDiscount = (discount: number): string => {
  return `${discount}% OFF`
}

describe('Utils', () => {
  describe('formatPrice', () => {
    it('should format price correctly', () => {
      expect(formatPrice(100)).toMatch(/^\$.*100$/)
      expect(formatPrice(50.5)).toMatch(/^\$.*50,5$/)
      expect(formatPrice(0)).toMatch(/^\$.*0$/)
    })

    it('should format price with different currency', () => {
      expect(formatPrice(100, 'USD')).toMatch(/^US\$.*100$/)
    })
  })

  describe('formatDiscount', () => {
    it('should format discount correctly', () => {
      expect(formatDiscount(20)).toBe('20% OFF')
      expect(formatDiscount(0)).toBe('0% OFF')
      expect(formatDiscount(50)).toBe('50% OFF')
    })
  })
}) 