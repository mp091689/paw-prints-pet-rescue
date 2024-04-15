<template>
  <div id="register" class="text-center">
    <form @submit.prevent="resetPassword">
      <h1>Reset Password</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrors }}
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Set Password</button>
    </form>
  </div>
</template>

<script>
import authService from '../../services/AuthService';

export default {
  data() {
    return {
      user: {
        token: this.$route.query.token,
        password: '',
        confirmPassword: '',
      },
      registrationErrors: '',
    };
  },
  methods: {
    resetPassword() {
      if (this.user.password !== this.user.confirmPassword) {
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService.resetPassword(this.user)
          .then(response => {
            if (response.status === 200) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>
