export type AnnotationSize = 'sm' | 'md' | 'lg';

export const annotationSizes = {
  sm: { container: 'size-6', icon: 15 },
  md: { container: 'size-8', icon: 20 },
  lg: { container: 'size-10', icon: 25 },
} as const;

