/** @type {import('ts-jest').JestConfigWithTsJest} */

const { defaults } = require("jest-config");

module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'node',
  moduleFileExtensions: [...defaults.moduleFileExtensions, 'test.ts'],
};