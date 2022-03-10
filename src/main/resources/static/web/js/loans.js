let app = new Vue ({
    el:'#app',
    data: {
        client:[],
        loan:[],
    },
    created(){
        this.loadData()
    },
    methods:{
        loadData(){
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
             axios.get(`http://localhost:8080/api/clients/current`)
             .then(response=>{
                 this.client=response.data
                  this.loan= response.data.clientLoan
 
                 console.log(response.data);
 
                console.log(this.loan)
 
             }) 
             .catch((error)=>{
                 console.log(error); //imprimir error si no funka
             })

        },
    },
})