import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router'
import Games from './components/games/Games'
import NewGame from './components/games/NewGame'
import Game from './components/games/Game'
import MainLayout from './components/layouts/MainLayout'

const router = createBrowserRouter([
  {
    element: <MainLayout />,
    children: [
      {
        index: true,
        Component: Games
      },
      {
        path: "games",
        index: true,
        Component: Games
      },
      {
        path: "games/new",
        Component: NewGame
      },
      {
        path: "games/:gameId",
        Component: Game
      }
    ]
  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
