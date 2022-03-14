let app = new Vue({
  el: '#app',
  data: {
    loanApplication:"",
    loan: [],
    hipotecario: [],
    personal: [],
    auto: [],
    ammount: 0,
    payments: [],
    destinyAccount: ""
  },
  created() {
    this.cargarLoan()
    this.cargarCuentas()
    },
  methods: {
    cargarLoan() {
      console.log("loan created");
      axios.get(`http://localhost:8080/api/loan`)
        .then(response => {
          this.hipotecario = response.data[0]
          this.personal = response.data[1]
          this.auto = response.data[2]
//          console.log(this.hipotecario);
//          console.log(this.personal);
//          console.log(this.auto);
//          console.log(response.data);
          this.loan = response.data
        })
        .catch((error) => {
          console.log(error); //imprimir error si no funka
        })},

    cargarCuentas() {
      axios.get(`http://localhost:8080/api/clients/current`)
      .then( response=>{
        this.destinyAccount = response.data.account
        console.log(this.destinyAccount);
      })},

     filtro(){
      this.payments = this.loan.filter((element)=>element.name==this.loanApplication)
      this.payments = this.payments[0].payments
     }
     
}});