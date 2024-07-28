import styles from "./TestForm.module.less"
import { useForm} from "react-hook-form"

const TestForm = (props) => {
  
    
    const {register, handleSubmit, formState: {errors}} = useForm();

    const onSubmit = (data) => {
        // e.preventDefault();

        console.log(data);



    }


    return (
        <form className={styles.testFormContainer}
        onSubmit={handleSubmit(onSubmit)}>

            <input 
                type="text" 
                placeholder="在此输入"
                {...register("firstname", {required: true})}
            />
            {errors.firstname && <p>First name is required</p>}

            <input 
                type="file" 
                placeholder="点我上传"
                {...register("filename", {required: true, value: "data"})}></input>
            {errors.filename && <p>File name is required</p>}


            <button type="submit">点我提交</button>
        </form>
    )
}

export default TestForm;
