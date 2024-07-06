import styles from "./userList.module.less"


const UserListItem = (props) => {

    const {text} = props;

    return (
        <div className={styles.userListItemContainer}>
            {text}
        </div>
    )
}


const UserList = (props) => {

    // const {title, listData} = props;

    const title = "This is the user list, which takes up 3/12 of the window. You might choose to use Lists and Dividers to display your users like so:"
    const listData = [
        {text: "gaga"},
        {text: "haha"},
        {text: "titi"}
    ]

    return  (
        <div className={styles.userListContainer}>
            <div className={styles.userListTitleContainer}>
                {title}
            </div>
            {
                listData.map((elem, index) => {
                    return (
                        <UserListItem 
                            text = {elem.text}>
                        </UserListItem>
                    )
                })
            }
        </div>
    )
}

export default UserList;