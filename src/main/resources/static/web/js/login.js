var app = new Vue ({
    el:'#app',
    data: {
       clients:[],
    },
    created(){
        this.loadData()
    },
    methods:{
       loadData(){
        axios.get(`/api/clients/current/`)
        .then(response =>{
        this.clients=response.data
        console.log(this.clients)
        })
        .catch(e=>console.log(e))
       },
       
       exitToPage(){
        console.log("chao </3")
        axios.post('/api/logout')
            .then(response => {
                return window.location.href = "/form.html"})
    },
    /* createAccount(){
            console.log("ola nuebo")
            axios.post('api/clients',`firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`)

            .then(response => {
                return window.location.href = "/web/LogIn.html"
            })
        } */
}  
 })
      