describe('login', () => {
  beforeEach(() => {
    cy.visit(Cypress.env('frontUrl') + "/login");
  })

  it('deactivates submit button if form is empty', () => {
    cy.get('[data-cy=login-button]').should('have.attr', 'disabled');
  })

  it('displays dialog if credentials are wrong', () => {
    cy.get('[id=email]').type("itachi@gmail.com");
    cy.get('[id=password]').type("konoha");
    cy.get('[data-cy=login-button]').click();
    cy.get('.error-dialog').should('be.visible');
  })

  it('redirects to home on login success', () => {
    cy.get('[id=email]').type("itachi@konoha.com");
    cy.get('[id=password]').type("Aa123456@");
    cy.get('[data-cy=login-button]').click();
    cy.url().should('contain', 'articles')
  })
})
