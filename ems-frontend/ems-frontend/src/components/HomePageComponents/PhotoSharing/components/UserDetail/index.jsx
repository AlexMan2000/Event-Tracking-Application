import styles from "./userDetail.module.less"

const UserDetail = (props) => {


    return (
        <div className={styles.userDetailContainer}>

me to your photosharing app! This Paper component displays the main content of the application. The sm={9} prop in the Grid item component makes it responsively display 9/12 of the window. The Switch component enables us to conditionally render different components to this part of the screen. You don't need to display anything here on the homepage, so you should delete this Route component once you get started.
        </div>
    )
    
}


export default UserDetail;