const state = {
  id: null,
  name: null,
  profile: null,
  email: null
}

const mutations = {
  Add_USER_INFO (state, {
    id,
    name,
    profile,
    email
  }) {
    state.id = id
    state.name = name
    state.profile = profile
    state.email = email
  }
}

const actions = {
  logout ({ commit }) {
    commit('Add_USER_INFO', {
      id: null,
      name: null,
      profile: null,
      email: null
    })
  }
}

const getters = {
  userInfo: ({
    id,
    name,
    profile,
    email
  }) => {
    return {
      id,
      name,
      profile,
      email
    }
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
