module.exports = {
  preset: '@vue/cli-plugin-unit-jest/presets/typescript-and-babel',
  testMatch: ['**/(*.)spec.js', '**/(*.)spec.ts'],
  moduleFileExtensions: ['js', 'ts', 'json', 'vue'],
  transform: {
    '^.+\\.vue$': 'vue-jest',
  },
  // testURL:
  //   process.env.API_BASE_URL || `http://localhost:${process.env.MOCK_API_PORT}`,
  moduleNameMapper: require('./aliases.config').jest,
  coverageDirectory: '<rootDir>/tests/unit/coverage',
  collectCoverageFrom: [
    'src/**/*.{js,ts,vue}',
    '!**/node_modules/**',
    '!src/main.ts',
    '!src/app.vue',
    '!src/router/index.ts',
    '!src/store/index.ts',
  ],
}
