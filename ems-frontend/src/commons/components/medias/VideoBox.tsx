
import { CSSProperties, useRef, useState } from "react";
import "./VideoBox.css"
import { classNamesArgs } from "@/commons/utils/classNameHandler";


interface VideoCardProps {
    src: string;
    width?: number;
    height?: number;
    className?: string;
    style?: CSSProperties;

}

const VideoBox = ({src, width, height, className, style}: VideoCardProps) => {


    const videoRef = useRef<HTMLVideoElement>(null);
    const [sourceError, setSourceError] = useState<boolean>(false);
    const [volumnBarVisible, setVolumnBarVisible] = useState<boolean>(false);
    const [volume, setVolume] = useState<number>(0);
    const [isPlay, setIsPlay] = useState<boolean>(false);


    const handlePlay = () => {
        if (videoRef.current) {
            if (isPlay) {
                console.log("ready to pause")
                handlePause();
            } else {
                videoRef.current.play();
                setIsPlay(true);
            }
        }
    }

    const handlePause = () => {
        if (videoRef.current) {
            videoRef.current.pause();
            setIsPlay(false);
        }
    }

    const handleMute = () => {
        if (videoRef.current) {
            videoRef.current.muted = true;
        }
    }


    const handleUnmute = () => {
        if (videoRef.current) {
            videoRef.current.muted = false;
        }
    }

    const handleVolume = (value: number) => {

        // Cap the volume
        let newVolume = Math.min(Math.max(0, value), 100);
        

        if (value > 1 && value <= 100) {
            newVolume = parseFloat((value / 100).toFixed(2));
        }

        if (videoRef.current) {
            videoRef.current.volume = newVolume;
        }
    }


    const handleError = () => {
        setSourceError(true);
    }



    const showVolumeBar = () => {
        setVolumnBarVisible(true);
    }


    return (
        <div className={classNamesArgs("video-box-container",className)}
        style={
            {width: `${width}px`, height: `${height}px`,...style}
        }>
            {
                !sourceError ?
                <video 
                    src={src}
                    onError={handleError}
                    ref = {videoRef} 
                    className={classNamesArgs("video-box-content", className)}>
                </video> : 
                <div className={classNamesArgs("video-box-error", className)}>
                </div>
            }
            <div className={classNamesArgs("video-box-control-groups", className)}> 
                <div className={classNamesArgs("video-box-control-left-group", className)}>
                    <div className={classNamesArgs("video-box-control-play", className)}
                        onClick = {handlePlay}
                    ></div>
                    
                    <div className={classNamesArgs("video-box-control-progress", className)}>
                    </div>
                    
                    <div className={classNamesArgs("video-box-control-speed", className)}
                        // onClick = {}
                    ></div>
                    
                </div>
                <div className={classNamesArgs("video-box-control-right-group", className)}>
                    <div className={classNamesArgs("video-box-control-volume", className)}
                        onClick = {showVolumeBar}
                    >
                        <div className={classNamesArgs("video-box-control-volume-bar", className)}
                        // onClick = {}
                        ></div>
                    </div>
                    <div className={classNamesArgs("video-box-control-resolution", className)}></div>

                    <div className={classNamesArgs("video-box-control-fullscreen", className)}></div>
                    {/* <div className={classNamesArgs("video-box-functional-like", className)}></div>
                    <div className={classNamesArgs("video-box-functional-share", className)}></div> */}
                </div>
            </div>
            
           
        </div>
    )
}

export default VideoBox;