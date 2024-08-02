import React from "react";
import "./header.css"
import Avator from "antd";
import { makeStyles } from '@mui/styles';
import { useSelector } from "react-redux";
import { RootState } from "../../../store/store";
import InteractiveAvatar from "./components/avatar";


const useStyles = makeStyles({
    headWrapper: {
      background: 'linear-gradient(to right, #0000FF, #00FFFF)',
      opacity: "0.5",
      border: 0,
      borderRadius: 3,
      boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
      color: 'white',
      height: 70,
      padding: '10px 30px 10px 10px',
      display: "flex",
      flexDirection: "row",
      justifyContent: "space-between"
    },
    userInfoWrapper: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        gap: "40px"
    },
    userTokens: {
        alignSelf: ""
    },
    userPlans: {

    }
  });
  


function HeaderComponent() {

    const styles = useStyles();

    const userInfo = useSelector((state: RootState) => state.auth.user);

    console.log(userInfo)

    return (
        <div className={styles.headWrapper}>
            <div className=""></div>
            <div className=""></div>
            <div className={styles.userInfoWrapper}>
                <div className={styles.userTokens}>
                    switch language
                </div>
                <div className={styles.userPlans}>
                    {userInfo.middleName}
                </div>
                <InteractiveAvatar />
            </div>
           
        </div>
    )

}


export default HeaderComponent;