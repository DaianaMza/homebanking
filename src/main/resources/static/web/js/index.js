var app = new Vue({
    el: '#app',
    data: {
        ocultar:false,
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        log: {
            email: '',
            password: '',
        },
        clients:[],
    },
    methods: {
    /*     loadData(){
            axios.get(`/api/clients`)
            .then(response =>{
            this.clients=response.data
            console.log(this.clients)
            })
            .catch(e=>console.log(e))
           }, */

        enterToPage() {

            console.log("hola <3")

            axios.post('/api/login', `email=${this.email}&password=${this.password}`, {
                headers: { 'content-type': 'application/x-www-form-urlencoded' }
            })

                .then(response => {
                    return window.location.href = "/web/LogIn.html"
                })
        },
        createAccount(){
            console.log("ola nuebo")
            axios.post('api/clients',`firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`,{
            headers: { 'content-type': 'application/x-www-form-urlencoded'}})
            .then(response => 
                this.enterToPage())
                .catch(error=>{
                    console.log(error)
                })
               /*  return window.location.href = "/web/LogIn.html" */
            }
        }
    })
    






// function setFormMessage(formElement, type, message) {
//     const messageElement = formElement.querySelector(".form__message");

//     messageElement.textContent = message;
//     messageElement.classList.remove("form__message--success", "form__message--error");
//     messageElement.classList.add(`form__message--${type}`);
// }

// function setInputError(inputElement, message) {
//     inputElement.classList.add("form__input--error");
//     inputElement.parentElement.querySelector(".form__input-error-message").textContent = message;
// }

// function clearInputError(inputElement) {
//     inputElement.classList.remove("form__input--error");
//     inputElement.parentElement.querySelector(".form__input-error-message").textContent = "";
// }

// document.addEventListener("DOMContentLoaded", () => {
//     const loginForm = document.querySelector("#login");
//     const createAccountForm = document.querySelector("#createAccount");

//     document.querySelector("#linkCreateAccount").addEventListener("click", e => {
//         e.preventDefault();
//         loginForm.classList.add("form--hidden");
//         createAccountForm.classList.remove("form--hidden");
//     });

//     document.querySelector("#linkLogin").addEventListener("click", e => {
//         e.preventDefault();
//         loginForm.classList.remove("form--hidden");
//         createAccountForm.classList.add("form--hidden");
//     });
// });