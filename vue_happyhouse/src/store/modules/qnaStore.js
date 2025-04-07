const qnaStore = {
  namespaced: true,
  state: {
    answers: [],
  },
  getters: {
    allTodosCount(state) {
      return state.todos.length;
    },
    completedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === true;
      }).length;
    },
    unCompletedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === false;
      }).length;
    },
    allAnswersCount(state) {
      return state.answers.length;
    },
  },
  mutations: {
    CREATE_ANSWER(state, answerItem) {
      console.log("create mutation");
      state.answers.push(answerItem);
    },
    DELETE_ANSWER(state, answerItem) {
      console.log("delete mutation");
      const index = state.answers.indexOf(answerItem);
      state.answers.splice(index, 1);
    },
    UPDATE_ANSWER_STATUS(state, answerItem) {
      console.log("update mutation");
      state.answers = state.answers.map((answer) => {
        if (answer === answerItem) {
          return {
            ...answer,
            completed: !answerItem.completed, //toggle
          };
        }
        return answer;
      });
    },
  },
  actions: {
    createAnswer({ commit }, answerItem) {
      commit("CREATE_ANSWER", answerItem);
    },
    deleteAnswer({ commit }, answerItem) {
      commit("DELETE_ANSWER", answerItem);
    },
    updateAnswerStatus({ commit }, answerItem) {
      commit("UPDATE_ANSWER_STATUS", answerItem);
    },
  },
};

export default qnaStore;
