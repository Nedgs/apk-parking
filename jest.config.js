const preset = require('jest-preset-angular/jest-preset');

module.exports = {
    ...preset,
    preset: 'jest-preset-angular',
    verbose: false,
    testEnvironment: 'jsdom',
    testRunner: 'jest-jasmine2',
    collectCoverage: true,
    collectCoverageFrom: ['src/app//*.ts'],
    coveragePathIgnorePatterns: ['.module.ts', '.routes.ts', '.routing.ts'],
    setupFilesAfterEnv: ['<rootDir>/setup-jest.js'],
    testMatch: ['/*.spec.ts'],
    globals: {
        ...preset.globals,
        'ts-jest': {
            ...preset.globals['ts-jest'],
            tsconfig: 'tsconfig.spec.json',
            isolatedModules: true
        }
    }
};
