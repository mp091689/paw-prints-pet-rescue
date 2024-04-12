import {createRouter as createRouter, createWebHistory} from 'vue-router'
import {useStore} from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/auth/LoginView.vue';
import LogoutView from '../views/auth/LogoutView.vue';
import RegisterView from '../views/auth/RegisterView.vue';
import AdoptView from "@/views/pet/AdoptView.vue";
import AddPetView from "@/views/pet/AddPetView.vue";
import EditPetView from "@/views/pet/EditPetView.vue";
import DonateView from "@/views/pet/DonateView.vue";
import VolunteerView from "@/views/volunteer/VolunteerView.vue";

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/login",
        name: "login",
        component: LoginView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/logout",
        name: "logout",
        component: LogoutView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/register",
        name: "register",
        component: RegisterView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/adopt",
        name: "adopt",
        component: AdoptView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/pet/add",
        name: "add-pet",
        component: AddPetView,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/pet/:petId/edit",
        name: "edit-pet",
        component: EditPetView,
        meta: {
            requiresAuth: true
        }
    },
    {path: "/donate",
        name: "donate",
        component: DonateView,
        meta: {
            requiresAuth: false
        }
    },
    {path: "/volunteer",
        name: "volunteer",
        component: VolunteerView,
        meta: {
            requiresAuth: false
        }
    },
];

// Create the router
const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

router.beforeEach((to) => {

    // Get the Vuex store
    const store = useStore();

    // Determine if the route requires Authentication
    const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

    // If it does and they are not logged in, send the user to "/login"
    if (requiresAuth && store.state.token === '') {
        return {name: "login"};
    }
    // Otherwise, do nothing and they'll go to their next destination
});

export default router;
