import  { requestPost, requestGet, requestPut } from '@/utils/request.js';
import SERVER from './drf.js';

export const userApi = {
  registerUser: (data) => {
    console.log('good')
    return requestPost(SERVER.URL + SERVER.ROUTES.signup, data);
  },


  getUser: (userId, headers) => {
    // 이거 유알엘 어떻게 하냐.
    // 근데 app.vue에서는 그냥 getUser 하나로만 갱신하는데....
    // url 바꿔주기.
    return requestGet(SERVER.URL + SERVER.ROUTES.getuserinf  + `/${userId}`, headers);
  },

  updateUserPassword: (data, headers) => {
    return requestPut(SERVER.URL + SERVER.ROUTES.changePWAfterLogin, data, headers);
  },

  updateUserProfileImg: (data, headers) => {
    return requestPut(SERVER.URL + SERVER.ROUTES.changeProfileImg, data, headers);
  },

}

export default userApi;