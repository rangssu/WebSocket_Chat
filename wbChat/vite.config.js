import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  define: {
    global: 'window', // ✅ 브라우저 환경에서 global을 window로 대체
  },
  plugins: [react()],
})
