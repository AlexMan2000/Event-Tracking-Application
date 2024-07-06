import styles from "./PhotoSharing.module.less"
import TopBar from "./components/TopBar";
import UserList from "./components/UserList";
import UserDetail from "./components/UserDetail";


const PhotoSharingPage = () => {


    // const 


    return (
        <div className={styles.photoSharingPageContainer}>
            <TopBar 
                title={"Welcome to photo sharing page"}
                backgroundColor={"#1976d2"}
                height={"70px"}></TopBar>
            <div className={styles.contentContainer}>
                <UserList></UserList>
                <UserDetail></UserDetail>
            </div>
        </div>
    )
}


export default PhotoSharingPage;