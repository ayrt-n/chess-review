import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTriangleExclamation } from '@fortawesome/free-solid-svg-icons';

interface MistakeProps {
  size?: AnnotationSize;
}

function MistakeMove({ size = 'md' }: MistakeProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div className={`${container} rounded-full bg-orange-500 flex items-center justify-center`} >
      <FontAwesomeIcon 
        icon={faTriangleExclamation} 
        style={{ 
          fontSize: `${icon}px`, 
          color: '#ffffff',
        }} 
      />
    </div>
  );
}

export default MistakeMove;
