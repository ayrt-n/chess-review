import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';

interface GoodProps {
  size?: AnnotationSize;
}

function GoodMove({ size = 'md' }: GoodProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div
      className={`${container} rounded-full bg-lime-500 flex items-center justify-center`}
    >
      <svg
        width={icon}
        height={icon}
        viewBox="0 0 24 24"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M7 22V11M2 13V20C2 21.1046 2.89543 22 4 22H17.4262C18.907 22 20.1662 20.9197 20.3914 19.4562L21.4683 12.4562C21.7479 10.6389 20.3418 9 18.5032 9H14V4C14 2.89543 13.1046 2 12 2C11.4477 2 11 2.44772 11 3V3.5C11 4.32843 10.7893 5.14262 10.3906 5.87104L8.14629 9.87926C7.74782 10.6074 7.53711 11.4214 7.53711 12.2496V22H7Z"
          fill="#ffffff"
          stroke="#ffffff"
          strokeWidth="1.5"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
      </svg>
    </div>
  );
}

export default GoodMove;

