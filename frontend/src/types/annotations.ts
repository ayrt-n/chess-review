export type AnnotationSize = 'sm' | 'md' | 'lg';

export const annotationSizes = {
  sm: { container: 'size-6', icon: 14 },
  md: { container: 'size-8', icon: 16 },
  lg: { container: 'size-10', icon: 18 },
} as const;

