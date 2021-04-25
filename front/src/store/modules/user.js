const state = {
  id: null,
  name: null,
  profile: null,
  email: null,
  description: null
}

const mutations = {
  Add_USER_INFO (state, {
    id,
    name,
    profile,
    email,
    description
  }) {
    state.id = id
    state.name = name
    state.profile = profile
    state.email = email
    state.description = description
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
    email,
    description
  }) => {
    return {
      id,
      name,
      profile,
      email,
      description
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
