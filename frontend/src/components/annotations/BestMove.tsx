import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStar } from '@fortawesome/free-solid-svg-icons';

interface BestProps {
  size?: AnnotationSize;
}

function BestMove({ size = 'md' }: BestProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div
      className={`${container} rounded-full bg-lime-500 flex items-center justify-center`}
    >
      <FontAwesomeIcon 
        icon={faStar} 
        style={{ 
          fontSize: `${icon}px`,
          color: '#ffffff' 
        }} 
      />
    </div>
  );
}

export default BestMove;