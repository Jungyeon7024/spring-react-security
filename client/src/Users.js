//import logo from './logo.svg';
import './App.css';
import React , {useState,useEffect} from 'react';
import axios from 'axios';


function Users() {
    const [data, setData] = useState([]);
    const [newUser, setNewUser] = useState ({username:'', email:''})
    useEffect(()=> {
      const fetchData =async ()=>{
        try{
          const res = await axios.get("http://localhost:8080/api/user", {
            withCredentials: true,
          }); 
          setData(res.data);
        }catch(error)
        {console.log(error);}
      };
      fetchData();
    },[]);

    const handleInputChange = (e) => {
      const{name,value} = e.target;
      setNewUser((prevUser)=>({...prevUser, [name]: value}));
    };
    const handleAddUser = async ()=>{
    
    try {
      const response= await axios.post(
      'http://localhost:8080/api/user',newUser,{withCredentials:true}
    );
    setData((prevUser)=>[...prevUser,response.data]); 
    setNewUser({username: '', email:''});
     } catch(error){
      console.error('데이터 부적합합니다.',error);
    }
  };
  


    /*
    useEffect(()=>{
      axios
      .get('http://localhost:8080/api/hello',{withCredentials:true})
      //response = res 는 같은 의미, 안에 변수 값은 정해진 것이 없지만 res, response를 사용하는게 좋다. 
      .then((res)=>{
        setData(res.data);
      })
      .catch((error)=>{
        console.log('데이터 없음', error);
      });
    },[]);
    */

  return (
    <div>
      <h1> API 호출 확인 </h1>
      <ul>
        {data.map((user) => (
          <li key={user.id}>
            {user.username}={user.email}
          </li>
        ))}
      </ul>
      <h2>새로운 유저 저장</h2>
      <div>
        <label>회원 이름 : </label>
        <input
          type="text"
          name="username"
          value={newUser.username}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label>회원 이메일 : </label>
        <input
          type="text"
          name="email"
          value={newUser.email}
          onChange={handleInputChange}
        />
      </div>
      <button onClick={handleAddUser}>유저 저장하기</button>
    </div>
  );
}





export default Users;