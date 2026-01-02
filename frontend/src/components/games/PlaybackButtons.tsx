import { 
  faBackwardStep, 
  faChevronLeft, 
  faChevronRight, 
  faForwardStep 
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

interface PlaybackButtonsProps {
  reset: () => void;
  prev: () => void;
  next: () => void;
  end: () => void;
}

function PlaybackButtons({ reset, prev, next, end }: PlaybackButtonsProps) {
  return (
    <div className="bg-zinc-950 p-7 flex justify-center gap-2">
      <button onClick={reset} className="bg-zinc-800 py-3 px-4 rounded-md border-b-4 border-zinc-900 hover:bg-zinc-750 hover:border-zinc-850 active:bg-zinc-800 active:border-zinc-900">
        <FontAwesomeIcon icon={faBackwardStep} />
      </button>

      <button onClick={prev} className="bg-zinc-800 py-3 px-4 rounded-md border-b-4 border-zinc-900 hover:bg-zinc-750 hover:border-zinc-850 active:bg-zinc-800 active:border-zinc-900">
        <FontAwesomeIcon icon={faChevronLeft} />
      </button>

      <button onClick={next} className="bg-zinc-800 py-3 px-4 rounded-md border-b-4 border-zinc-900 hover:bg-zinc-750 hover:border-zinc-850 active:bg-zinc-800 active:border-zinc-900">
        <FontAwesomeIcon icon={faChevronRight} />
      </button>

      <button onClick={end} className="bg-zinc-800 py-3 px-4 rounded-md border-b-4 border-zinc-900 hover:bg-zinc-750 hover:border-zinc-850 active:bg-zinc-800 active:border-zinc-900">
        <FontAwesomeIcon icon={faForwardStep} />
      </button>
    </div>
  );
}

export default PlaybackButtons;
