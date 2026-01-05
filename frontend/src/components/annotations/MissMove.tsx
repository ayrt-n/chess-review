import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsDown } from '@fortawesome/free-solid-svg-icons';

interface MissProps {
  size?: AnnotationSize;
}

function MissMove({ size = 'md' }: MissProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div className={`${container} rounded-full bg-rose-600 flex items-center justify-center`} >
      <FontAwesomeIcon 
        icon={faThumbsDown} 
        style={{ 
          fontSize: `${icon}px`, 
          color: '#ffffff'
        }} 
      />
    </div>
  );
}

export default MissMove;
