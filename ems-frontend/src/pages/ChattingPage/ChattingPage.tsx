import styles from "./ChattingPage.module.less"
import ChattingModal from "./components/ChattingModal";
import { ChatBotModalProvider } from "./components/ChattingModalContext";


const ChattingPage = () => {



    return (
        <div className={styles.container}>
            <ChatBotModalProvider>
                <ChattingModal></ChattingModal>
            </ChatBotModalProvider> 
        </div>
    )
}

export default ChattingPage;