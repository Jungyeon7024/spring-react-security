// npm i react-kakao-login

//0f920b9d4ff31257a101469ab4c91926


import React from "react";
import KakaoLogin from "react-kakao-login";
import kakaoImage from ".src/png/kakao_login_medium_narrow.png"; 

const KakaoApp = () => {
  const KakaoLoginSuccess = (res) => {
    console.log(res);
  };
  const KakaoLoginFailure = (err) => {
    console.error(err);
  };

  return (
    <div>
      <KakaoLogin
        token="0f920b9d4ff31257a101469ab4c91926"
        onSuccess={KakaoLoginSuccess}
        onFailure={KakaoLoginFailure}
        // getProfile={true}
        render={(props) => (
          <button onClick={props.onClick}>
            <img src={kakaoImage} alt="Kakao Login" />
            카카오 로그인
          </button>
        )}
      />
    </div>
  );
};

export default KakaoApp;