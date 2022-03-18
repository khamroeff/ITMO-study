<template>

  <!-- TODO: do proper design for this shit -->

  <div class="page-size-container d-flex align-items-center justify-content-center flex-column">
    <div class="d-flex flex-column align-items-center gap-2">
      <img src="img/av3.jpg" width="300" class="logo shadow" alt="logo"/>

      <h1 class="main-title">Лаба 4</h1>
      <h3 class="box-visual invert-colors name-plate">Хамроев Салимджон <br> P3231 <br> Вариант 735</h3>
      <br>
      <input type="text" placeholder="Логин" class="text-input" v-model="login">
      <input type="password" placeholder="Пароль" class="text-input" v-model="password">

      <div>
        <button type="button" class="btn confirm-colors" v-on:click="logIn">Войти</button>
        <button type="button" class="btn reset-colors" v-on:click="register">Зарегистрироваться</button>
      </div>
    </div>
  </div>


</template>

<script>
export default {
  name: "StartPage",
  data() {
    return {
      login: '',
      password: ''
    }
  },
  methods: {
    logIn() {
      fetch('http://localhost:3439/lab4back-1.0-SNAPSHOT/api/user', {
        credentials: 'include',
        method: "POST",
        body: new URLSearchParams({
          login: this.login,
          pass: this.password
        })
      }).then(res => {
        if (res.ok) {
          this.$router.push("/main")
        } else alert("Неверный логин или пароль")
      })
    },
    register() {
      fetch('http://localhost:3439/lab4back-1.0-SNAPSHOT/api/user/reg', {
        credentials: 'include',
        method: "POST",
        body: new URLSearchParams({
          login: this.login,
          pass: this.password
        })
      }).then(res => {
        if (res.ok) {
          this.$router.push("/main")
        } else alert("Этот логин уже кем-то занят...")
      })
    }
  }
}
</script>

<style scoped>
.h1 {
  font-size: 32pt;
}

.page-size-container {
  width: 100%;
  height: calc(100vh - 16px);
  overflow: auto;
}

.d-flex {
  display: flex;
}

.flex-column {
  flex-direction: column;
}

.align-items-center {
  align-items: center;
}

.justify-content-center {
  justify-content: center;
}

.gap-2 {
  gap: 0.75rem
}
</style>