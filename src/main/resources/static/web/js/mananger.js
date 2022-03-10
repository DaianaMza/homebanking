var app = new Vue ({
    el:'#app',
    data: {
        client:[],
        pre:[],
        firstName:"",
        lastName:"",
        email:"",
        password:"",
        newClient:{},

    },
  
//momento de llorar por no saber como seguir
created(){
    this.loadData()
},
methods:{
     loadData(){
    axios.get("/clients")
    .then((response)=>{
        this.client=response.data._embedded.clients
        this.pre=response.data
    })
    .catch((error)=>{
        console.log(error); //imprimir error si no funka
    })
},

    addClient(){
        axios.post("/clients" ,{
                firstName:this.firstName,
                lastName:this.lastName,
                email:this.email,
                password:this.password
            })
        },
        
}
})
