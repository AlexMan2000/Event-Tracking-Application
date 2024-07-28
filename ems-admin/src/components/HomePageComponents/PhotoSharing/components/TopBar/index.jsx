import styles from "./topBar.module.less"


const TopBar = (props) => {


    const {height, backgroundColor, title} = props;

    return (
        <div className={styles.topBarContainer}
            style={{
                height: height, 
                backgroundColor: backgroundColor
            }}
            >
            <h1 className={styles.topBarText}>{title}</h1>
        </div>
    )


}


export default TopBar;