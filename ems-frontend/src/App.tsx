import { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { IntlProvider } from 'react-intl'
import LandingPage from './pages/LandingPage/LandingPage'
import ChattingPage from './pages/ChattingPage/ChattingPage'

const messages = {
  en: {
  },
  zh: {
  },
}



function App() {
  const dispatch = useDispatch();
  

  useEffect(() => {

  })

  const locale = "zh"
  return (
    <IntlProvider locale={locale} messages={messages[locale]}>
      <BrowserRouter>
       <Routes>
          <Route path="/" element={<LandingPage/>} />
          <Route path="/chat" element={<ChattingPage/>}/>
        </Routes>
      </BrowserRouter>
    </IntlProvider>
  )
}

export default App
