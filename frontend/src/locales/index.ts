import { createI18n } from 'vue-i18n'

const i18n = createI18n({
  locale: 'pt-BR',
  messages: {
    en: require('./en.json'),
    'pt-BR': require('./pt-BR.json')
  }
})

export default i18n
